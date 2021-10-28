// 绑定全局视频播放对象
window.krVideo = window.krVideo || {
    _prefix_id: 'krkr_video',
    _prefix: '/video/',
    play: function(src, loop){
        if(window.splayer) {
            // 由于某移动端IE6的内核太烂，所以这里使用安卓原生播放器
            try{
                // 被注入到全局的安卓播放器对象
                // 下面要进行全屏播放，要先计算可视区域的宽高，确定坐标
                let CANVAS_WIDTH = 1280;
                let left = (document.documentElement.clientWidth - CANVAS_WIDTH) / 2;
                window.splayer.play(left, src, false);
            }catch(err){
                console.log(err);
            }
        }else{
            loop = loop || false;
            let current_tag = document.getElementById(this._prefix_id)
            if(current_tag){
                document.body.removeChild(current_tag);
            }
            current_tag = document.createElement('video');
            current_tag.id = this._prefix_id;
            current_tag.width = 1280;
            current_tag.height = 720;
            let video_source = document.createElement('source');
            video_source.src = this._prefix + src;
            video_source.type = "video/mp4";
            current_tag.appendChild(video_source);
            current_tag.style.zIndex = 100;// 层级高度拉高点，免得被挡住
            document.getElementById('canvas').style.zIndex = -1;// canvas的层级降低
            current_tag.className = 'emscripten';
            document.body.appendChild(current_tag);
            current_tag.onended = function(){
                document.body.removeChild(current_tag);
                document.getElementById('canvas').style.zIndex = 0;// 恢复canvas的层级
            }
            alert(this._prefix + src);
            current_tag.play();
        }
    },
    // 直接卸载整个标签
    stop: function(){
        let video_tag = document.getElementById(this._prefix_id);
        if(video_tag){
            document.body.removeChild(video_tag);
        }
    },
    pause: function(){
        let video_tag = document.getElementById(this._prefix_id);
        if(current_tag){
            video_tag.pause();
        }
    },
    // 设置音量
    setVolume: function(vol) {
        let video_tag = document.getElementById(this._prefix_id);
        if(video_tag) {
            video_tag.volume = vol;
        }
    }
};
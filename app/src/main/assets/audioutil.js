// 绑定全局音频播放对象
window.krAudio = window.krAudio || {
    _tracks: [],
    _prefix: '/audio/',
    play: function(src, track, loop){
        loop = loop || false;
        if(this._tracks[track]){
            // 音轨已存在，可以直接play
            this._tracks[track].src = this._prefix + src;
            this._tracks[track].loop = loop;
            this._tracks[track].play();
        }else{
            // 音轨不存在，先创建audio
            let audio = new Audio(this._prefix + src);
            audio.loop = loop;
            this._tracks[track] = audio;
            audio.play();
        }
    },
    stop: function(track){
        // TODO audio没有stop功能，这里先这样做着先
        if(this._tracks[track]) {
            this._tracks[track].pause();
            this._tracks[track].src = '';
        }
    },
    pause: function(track){
        if(this._tracks[track]) {
            this._tracks[track].pause();
        }
    },
    // 设置音量
    setVolume: function(track, vol) {
        if(this._tracks[track]) {
            this._tracks[track].volume = vol;
        }
    }
};
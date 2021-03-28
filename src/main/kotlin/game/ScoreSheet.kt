package game

import core.Frame

class ScoreSheet(val player: String) {

    val frames = Array(10) {
        Frame(it + 1)
    }

    fun setShot(position: Int, shotScore: Int) {
        val currentFrame = frames[position]

        if (currentFrame.first == null) {
            currentFrame.first = shotScore

        } else if (currentFrame.second == null) {
            currentFrame.second = shotScore
        }
    }

    // smart shot
    fun setShot(shotScore: Int) {

        val currentFrame = getNextFrame()

        //TODO fix != null
        if (currentFrame != null) {
            //TODO fix != null
            if (currentFrame.first == null) {
                currentFrame.first = shotScore

                //TODO fix != null
            } else if (currentFrame.second == null) {
                currentFrame.second = shotScore

                //TODO fix != null
            } else if (currentFrame.third == null) {
                currentFrame.third = shotScore
            }
        }
    }

    private fun getNextFrame(): Frame? {
        return frames.find { !it.complete() }
    }

    fun getScore(index: Int): Int {
        val frameScore = frames[index].getScore()

        val res =
            if (frameScore < 10) {
                frameScore
            } else
                if (frameScore == 10 && frames[index + 1].first == null) {
                    0 //TODO porq dependemos del valor sig para poder calcular
                    // en realiadd esto no deberia darse ponele mmm o lanzamos una exception ?
                } else {
                    //TODO remove !!
                    frameScore + frames[index + 1].first!!
                }

        return res
    }

    override fun toString(): String {
        return "PlayerFrames(player='$player', frames=${frames.contentToString()})"
    }


}
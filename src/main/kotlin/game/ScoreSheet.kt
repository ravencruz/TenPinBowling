package game

import core.BowlingFrame

class ScoreSheet(val player: String) {

    val frames = Array(10) {
        BowlingFrame(it + 1)
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

    //TODO simplify
    private fun getNextFrame(): BowlingFrame? {
        return frames.find { !it.complete() }
    }

    fun getScore(frame: Int): Int {
        return frames[frame - 1].frameScore
    }

    override fun toString(): String {
        return "PlayerFrames(player='$player', frames=${frames.contentToString()})"
    }


}
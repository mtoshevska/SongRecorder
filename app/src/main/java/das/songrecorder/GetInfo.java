package das.songrecorder;

/**
 * Created by Toni on 30.12.2016.
 */

public interface GetInfo {
    /**
     * Gets information from external database for specified song.
     * @param f Song that should be filled with information
     * @return Song filled with information
     */
    void getDataFromDatabase(Song f);
}

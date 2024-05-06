package graph;
/**
 * Interface to be implemented by class(es) that represent
 * information about stations
 *
 * You may change the package name for this, but you should not
 * modify it in any other way.
 *
 */
public interface IStationInfo {

    /**
     * @return the name of the station
     */
    String getName();
    /**
     * @return x position -- 0 <= getxPos() < 256
     */
    int getxPos();
    /**
     * @return y position -- 0 <= getyPos() < 256
     */
    int getyPos();
}


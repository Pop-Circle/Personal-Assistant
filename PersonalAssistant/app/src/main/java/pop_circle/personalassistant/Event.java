package pop_circle.personalassistant;

/**
 * Created by Jackie on 2015-09-26.
 */

/* Stores the information for the event to go into the database.... I think? */
public class Event {
    private int id;
    private String eventName;
    private String eventTime;
    private String eventDesc;
    private String eventDate;
    private String eventOwnerID;
    private String eventRem;

    public Event()
    {
        this.eventName = null;
        this.eventTime = null;
        this.eventDesc = null;
        this.eventDate = null;
        this.eventOwnerID = null;
        this.eventRem = null;
    }

    public Event(int _id, String _eventName, String _eventTime,
                 String _eventDesc,String _eventDate, String _eventOwnerID
                ,String _eventRem)
    {
        super();
        this.id = _id;
        this.eventName = _eventName;
        this.eventTime = _eventTime;
        this.eventDesc = _eventDesc;
        this.eventDate = _eventDate;
        this.eventOwnerID = _eventOwnerID;
        this.eventRem = _eventRem;
    }
    public Event(String _eventName, String _eventTime,
                 String _eventDesc,String _eventDate, String _eventOwnerID
                ,String _eventRem)
    {
        super();
        this.eventName = _eventName;
        this.eventTime = _eventTime;
        this.eventDesc = _eventDesc;
        this.eventDate = _eventDate;
        this.eventOwnerID = _eventOwnerID;
        this.eventRem = _eventRem;
    }

    // ID
    public int getID(){
        return this.id;
    }
    public void setID(int _id){
        this.id = _id;
    }

    // EVENT NAME
    public String getEventName(){
        return this.eventName;
    }
    public void setEventName(String _eventName){
        this.eventName = _eventName;
    }

    // EVENT DESCRIPTION
    public String getEventDesc(){
        return this.eventDesc;
    }
    public void setEventDesc (String _eventDesc){
        this.eventDesc = _eventDesc;
    }

    // EVENT DATE
    public String getEventDate(){
        return this.eventDate;
    }
    public void setEventDate(String _eventDate){
        this.eventDate = _eventDate;
    }

    // EVENT OWNER ID
    public String getEventOwnerID(){
        return this.eventOwnerID;
    }
    public void setEventOwnerID(String _eventOwnerID){
        this.eventOwnerID = _eventOwnerID;
    }

    // EVENT TIME
    public String getEventTime(){
        return this.eventTime;
    }
    public void setEventTime(String _eventTime){
        this.eventTime = _eventTime;
    }

    // EVENT REM
    public String getEventRem(){
        return this.eventRem;
    }
    public void setEventRem(String _eventRem){
        this.eventRem = _eventRem;
    }

}

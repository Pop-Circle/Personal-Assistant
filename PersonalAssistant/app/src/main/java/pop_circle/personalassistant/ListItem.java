package pop_circle.personalassistant;


public class ListItem {
    public String title;
    public String subTitle;
    public String agendaID;
    // default constructor
    public ListItem() {
        this("Title", "Subtitle","agendaID");
    }

    // main constructor
    public ListItem(String title, String subTitle, String agendaID) {
        super();
        this.title = title;
        this.subTitle = subTitle;
        this.agendaID = agendaID;
    }

    // String representation
    public String toString() {
        return this.title + " # " + this.subTitle + " # " + this.agendaID;
    }
}
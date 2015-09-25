package pop_circle.personalassistant;

/**
 * Created by Stashie on 2015/09/22.
 */
public class Task {
    private String taskName;
    private int checked;
    private int id;

    public Task()
    {
        this.taskName=null;
        this.checked = 0;
    }

    public Task(String _taskName, int _checked)
    {
        super();
        this.taskName = _taskName;
        this.checked = _checked;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int _id)
    {
        this.id = _id;
    }

    public String getName()
    {
        return taskName;
    }

    public void setName(String _name)
    {
        this.taskName = _name;
    }

    public int getChecked()
    {
        return checked;
    }

    public void setChecked(int _check)
    {
        this.checked = _check;
    }
}

package spring;

public class Greeting {

    private long id;
    private String code;
    private String state;

    public Greeting() { }
    
    public Greeting(long id, String content, String state) {
        this.id = id;
        this.code = content;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
    
    public String getState() {
    	return state;
    }
}
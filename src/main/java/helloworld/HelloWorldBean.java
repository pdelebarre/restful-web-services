package helloworld;

public class HelloWorldBean {
    private final String message;

    public HelloWorldBean(String hello_world) {
        this.message=hello_world;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }
}

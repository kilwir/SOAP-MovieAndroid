package tmtc.soap.Model;

/**
 * Bad Boys Team
 * Created by remyjallan on 04/03/2016.
 */
public class ErrorContainer {
    public int Code;
    public String Message;

    public ErrorContainer(int code, String message){
        this.Code = code;
        this.Message = message;
    }

    @Override
    public String toString() {
        return "ERROR --> " + Code + " --> " + Message;
    }
}

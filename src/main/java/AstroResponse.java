import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class AstroResponse {
    private int number;
    private String message;
    private List<Assignment> people;

    public AstroResponse () {
    }

    public AstroResponse (final int number, final String message, final List<Assignment> people) {
        this.number = number;
        this.message = message;
        this.people = people;
    }

    public static AstroResponse fromJSON (final String s) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(s, AstroResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumber () {
        return number;
    }

    public void setNumber (final int number) {
        this.number = number;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (final String message) {
        this.message = message;
    }

    public List<Assignment> getPeople () {
        return people;
    }

    public void setPeople (final List<Assignment> people) {
        this.people = people;
    }
}

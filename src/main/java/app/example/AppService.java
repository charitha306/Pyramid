package app.example;

import app.example.models.ErrorResponse;
import app.example.models.IsPyramidResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/")
public class AppService {

    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/ispyramid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDomains(@QueryParam("word") String word) throws JsonProcessingException {

        if(word == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mapper.writeValueAsString(new ErrorResponse("Request must contain a word")))
                    .build();
        }

        //assume that the word should always contain lowercase letters, return an error state otherwise
        if(!word.matches("[a-z]*")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mapper.writeValueAsString(
                            new ErrorResponse("Invalid word - must contain only lower case letters")))
                    .build();
        }

        //word is said to be pyramid, if you can arrange the letters in increasing frequency, starting with 1
        //and continuing without gaps and without duplicates
        Map<Character, Integer> charMap = new HashMap();

        //insert each character to map, with its count
        for(int i = 0 ; i < word.length() ; i++) {
            char character = word.charAt(i);
            int count = charMap.getOrDefault(character, 0);
            count++;
            charMap.put(character, count);
        }

        List<Integer> counts = new ArrayList<Integer>(charMap.values());
        Collections.sort(counts); // sort the count values got from map

        //check whether the counts starts from 1 and increasing by only one until the end
        for(int i = 0 ; i < counts.size() ; i++) {
            if(i + 1 != counts.get(i)) {
                return Response.ok(mapper.writeValueAsString(new IsPyramidResponse(false))).build();
            }
        }
        return Response.ok(mapper.writeValueAsString(new IsPyramidResponse(true))).build();
    }

}

package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SpeechToText service = new SpeechToText();
		    service.setUsernameAndPassword("8e44b6e2-e113-44b6-8a59-8cf19f2e0bf2", "237hKKWAKnDq");

		    File audio = new File("audio/output.wav");
		    RecognizeOptions options = null;
			try {
				options = new RecognizeOptions.Builder()
					.model("ja-JP_BroadbandModel")
				    .audio(audio)
				    .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
				    .build();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    SpeechRecognitionResults transcript = service.recognize(options).execute();

		    System.out.println(transcript);
		    String s = String.valueOf(transcript);
		    ObjectMapper mapper = new ObjectMapper();
		    
		    try {
				JsonNode node = mapper.readTree(s);
				for (int i = 0; i < node.get("results").size(); i++) {
				String text = node.get("results").get(0).get("alternatives").get(0).get("transcript").toString();
				String confidence = node.get("results").get(0).get("alternatives").get(0).get("confidence").toString();
				System.out.println("transcript" + text);
				System.out.println("confidence" + confidence);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}

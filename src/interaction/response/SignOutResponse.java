package interaction.response;

import java.io.PrintWriter;
import java.util.Scanner;

import app.Server;
import app.ServerResponser;
import data.EResponse;
import data.User;
import interaction.IResponse;

public final class SignOutResponse implements IResponse{

	@Override
	public EResponse response(String[] params, ServerResponser responser, Scanner reader, PrintWriter writer) {
		
		EResponse response = null;
		
		User me = responser.getMeOrNull();
		
		if (me == null) { // 그냥 에러 

			response = EResponse.SIGNOUT_ERR_NOT_IN;
		} else if (!me.isSignedIn()) { // 로그인 한 적 없음 

			response = EResponse.SIGNOUT_ERR;
		} else { // 성공
			
			response = EResponse.SIGNOUT_OK;
			
			me.signOutNow();
			responser.setMe(null);
			
			Server.Announcers.remove(me.uid);

			for (String fUid : me.friends) {
				
				PrintWriter fWriter = Server.Announcers.getOrDefault(fUid, null);
				if (fWriter != null) {
					
					fWriter.print(EResponse.ANNOUNCE_FRIEND_OUT);
					fWriter.print(' ');
					
					fWriter.print(1);
					fWriter.print(' ');
					
					fWriter.println(me.uid);
					fWriter.flush();
				}
			}
		}
		
		if (writer != null) {
			
			writer.println(response.getValue());
			writer.flush();
		}
		
		return response;
	}
}

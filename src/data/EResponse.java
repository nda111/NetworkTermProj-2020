package data;

public enum EResponse {

	ANNOUNCE_ADD_FRIEND(ERequest.ANNOUNCE, (byte)0), // 庁姥 蓄亜 
	ANNOUNCE_FRIEND_IN(ERequest.ANNOUNCE, (byte)1),  // 庁姥 羨紗 
	ANNOUNCE_FRIEND_OUT(ERequest.ANNOUNCE, (byte)2), // 庁姥 尻衣 背薦 
	
	ANNOUNCE_ASK_CHAT(ERequest.ANNOUNCE, (byte)3), // 企鉢 重短 穿含 
	ANNOUNCE_ACK_CHAT(ERequest.ANNOUNCE, (byte)4), // 企鉢 重短 衣引 穿含 
	ANNOUNCE_SAY_CHAT(ERequest.ANNOUNCE, (byte)5), // 企鉢 鎧遂 穿含 
	ANNOUNCE_END_CHAT(ERequest.ANNOUNCE, (byte)6), // 企鉢 曽戟 穿含 

	ECHO_OK(ERequest.ECHO, (byte)0), // 拭坪 誓岩
	
	QUIT_OK(ERequest.QUIT, (byte)0), // 曽戟 呪喰 
	
	VALIDATE_UID_OK(ERequest.VALIDATE_UID, (byte)0), // UID 紫遂 亜管 
	VALIDATE_UID_NO(ERequest.VALIDATE_UID, (byte)1), // UID 紫遂 災亜管
	
	SIGNUP_OK(ERequest.SIGNUP, (byte)0),	// 亜脊 失因 
	SIGNUP_ERR(ERequest.SIGNUP, (byte)1), 	// 亜脊 叔鳶 
	
	SIGNIN_OK(ERequest.SIGNIN, (byte)0),			// 稽益昔 失因 
	SIGNIN_ERR_PW(ERequest.SIGNIN, (byte)1),		// 稽益昔 搾腔腰硲 堂
	SIGNIN_ERR_MULTI(ERequest.SIGNIN, (byte)2),		// 稽益昔 食君 奄奄 
	SIGNIN_ERR_NO_UID(ERequest.SIGNIN, (byte)3),	// 稽益昔 蒸澗 域舛
	
	SIGNOUT_OK(ERequest.SIGNOUT, (byte)0),			// 稽益焼数 失因 
	SIGNOUT_ERR_NOT_IN(ERequest.SIGNOUT, (byte)1), 	// 稽益焼数 叔鳶 (稽益昔 廃 旋 蒸製)
	SIGNOUT_ERR(ERequest.SIGNOUT, (byte)2),			// 稽益焼数 叔鳶 (益 須 戚政)

	ASK_UID_OK(ERequest.ASK_UID, (byte)0),	// 赤製
	ASK_UID_NO(ERequest.ASK_UID, (byte)1),	// 蒸製 
	
	ASK_FRIEND_OK(ERequest.ASK_FRIEND, (byte)0),	// しせ 
	ASK_FRIEND_NO(ERequest.ASK_FRIEND, (byte)1),	// いい 
	
	ADD_FRIEND_OK(ERequest.ADD_FRIEND, (byte)0),			// 失因 
	ADD_FRIEND_ERR_UID(ERequest.ADD_FRIEND, (byte)1),		// 益訓 蕉 蒸製 
	ADD_FRIEND_ERR_ALREADY(ERequest.ADD_FRIEND, (byte)2),	// 戚耕 蓄亜敗 
	ADD_FRIEND_ERR_YOU(ERequest.ADD_FRIEND, (byte)3),		// 沙昔 庁姥 蓄亜 
	ADD_FRIEND_ERR(ERequest.ADD_FRIEND, (byte)4),			// 拭君 

	ASK_CHAT_OK(ERequest.ASK_CHAT, (byte)0), 		// しせ 弘嬢瑳惟 
	ASK_CHAT_OFFLINE(ERequest.ASK_CHAT, (byte)1), 	// 安 神覗虞昔戚虞 照 喫 
	ASK_CHAT_ERR(ERequest.ASK_CHAT, (byte)2), 		// 拭君 概生艦猿 陥獣 背坐 

	ACK_CHAT_OK(ERequest.ASK_CHAT, (byte)0),		// しせ 穿含拝惟 
	ACK_CHAT_LATE(ERequest.ASK_CHAT, (byte)1),		// 簡醸嬢 
	
	SAY_CHAT_OK(ERequest.SAY_CHAT, (byte)0),		// 源馬奄 しせ 
	SAY_CHAT_NO_ROOM(ERequest.SAY_CHAT, (byte)1), 	// 号 蒸嬢像 
	SAY_CHAT_ERR(ERequest.SAY_CHAT, (byte)2),		// 拭君 

	END_CHAT_OK(ERequest.END_CHAT, (byte)0),	// しせ 
	END_CHAT_ERR(ERequest.END_CHAT, (byte)1);	// 拭君 

	// 督虞耕斗稽 級嬢紳 葵拭 背雁馬澗 ERequest 梓端研 鋼発廃陥.
	// 硝 呪 蒸澗 推短戚檎 null聖 鋼発廃陥.
	// ex) value == 0 -> return ECHO
	public static EResponse valueOf(short value) {
		
		for (EResponse v : EResponse.values()) {
			
			if (v.getValue() == value) {
				
				return v;
			}
		}
		
		return null;
	}
	
	private short value = -1; // 推短税 縦紺
	
	// 持失
	private EResponse(ERequest request, byte value) {
		
		this.value = (short) ((request.getValue() << 8) | value);
		
		// 0000 0000 | 0000 0000
		// 推短 ID   | Yes/No/...
	}
	
	// 戚 推短税 縦紺切研 亜閃紳陥.
	public short getValue() {
		
		return value;
	}
	
	// 戚 誓岩戚 嬢恐 推短拭 企廃 誓岩昔走 亜閃紳陥.
	public ERequest getRequest() {
		
		final byte requestId = (byte) (value >> 8);
		
		return ERequest.valueOf(requestId);
	}
	
	// 推短拭 企背 嬢恐 誓岩昔走 亜閃紳陥.
	public byte getResponse() {
		
		return (byte) (value & 0xFFFFFF);
	}
}

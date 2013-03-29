package com.gpl.rpg.AndorsTrail.conversation;

import java.util.Collection;
import java.util.HashMap;

import android.content.res.Resources;

import com.gpl.rpg.AndorsTrail.resource.ResourceLoader;
import com.gpl.rpg.AndorsTrail.resource.parsers.ConversationListParser;

public final class ConversationLoader {
	private final HashMap<String, Integer> resourceIDsPerPhraseID = new HashMap<String, Integer>();

	public void addIDs(int resourceId, Collection<String> ids) {
		for(String s : ids) resourceIDsPerPhraseID.put(s, resourceId);
	}
	
	public Phrase loadPhrase(String phraseID, ConversationCollection conversationCollection, Resources r) {
		if (conversationCollection.hasPhrase(phraseID)) {
			return conversationCollection.getPhrase(phraseID);
		}
		
		ConversationListParser conversationListParser = new ConversationListParser();
		int resourceID = resourceIDsPerPhraseID.get(phraseID);
		conversationCollection.initialize(conversationListParser, ResourceLoader.readStringFromRaw(r, resourceID));
		
		return conversationCollection.getPhrase(phraseID);
	}
}

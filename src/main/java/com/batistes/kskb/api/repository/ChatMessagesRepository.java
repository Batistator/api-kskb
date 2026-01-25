package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.entity.ChatMessages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {
    
    @Query("SELECT c FROM ChatMessages c WHERE c.matchChecksum IN (:matchChecksumList)")
           public List<ChatMessages> getChatMessagesByMatch(List<String> matchChecksumList);

}
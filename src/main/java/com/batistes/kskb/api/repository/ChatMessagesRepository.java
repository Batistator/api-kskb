package com.batistes.kskb.api.repository;

import com.batistes.kskb.api.dto.ChatMessageDTO;
import com.batistes.kskb.api.entity.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {
    @Query(value="SELECT match_checksum as matchChecksum, sender_name as player, message as messageString, sender_is_alive as isAlive, " +
            "CASE WHEN cm.sender_side = '2' THEN '#de9b35' ELSE '#5d79ae' END as color, " +
            "tick as tick " +
            "FROM chat_messages cm WHERE cm.match_checksum = :matchChecksum", nativeQuery = true )
    public List<ChatMessageDTO> findMessagesByMatch(String matchChecksum);

    @Query(value= "SELECT match_checksum as matchChecksum, sender_name as player, message as messageString, sender_is_alive as isAlive, " +
            "CASE WHEN cm.sender_side = '2' THEN '#de9b35' ELSE '#5d79ae' END as color, " +
            "tick as tick " +
            "FROM chat_messages cm WHERE cm.match_checksum IN :matchChecksumList", nativeQuery = true )
    public List<ChatMessageDTO> findMessagesByMatches(List<String> matchChecksumList);
}
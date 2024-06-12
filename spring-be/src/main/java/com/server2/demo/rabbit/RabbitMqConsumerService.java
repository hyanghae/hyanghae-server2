package com.server2.demo.rabbit;

import com.server2.demo.member.domain.Member;
import com.server2.demo.member.service.MemberService;
import com.server2.demo.member.setting.SettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMqConsumerService {

    private final SettingService settingService;
    private final MemberService memberService;


    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMember(MemberDto memberDto) {
        log.info("Received Message : {}", memberDto.toString());
        Member member = memberService.findMemberById(memberDto.memberId);
        settingService.refreshData(member);


    }


  /*   //RabbitMQ로부터 메시지를 수신하여 처리하는 메서드
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto messageDto) {
        log.info("Received Message : {}", messageDto.toString());
    }*/

}
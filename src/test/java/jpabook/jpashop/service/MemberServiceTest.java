package jpabook.jpashop.service;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest
{
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    void 회원가입() throws Exception
    {
        //given
        Member member = new Member();
        member.setName("1hoon");

        //when
        Long joinId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(joinId));
    }

    @Test
    void 중복_회원_예외() throws Exception
    {
        //given
        String name = "1hoon";

        Member memberA = new Member();
        memberA.setName(name);

        Member memberB = new Member();
        memberB.setName(name);

        //when
        memberService.join(memberA);
        try {
            memberService.join(memberB);
        }catch (IllegalStateException e){
            return;
        }


        //then
       fail("예외 발생");
    }
}
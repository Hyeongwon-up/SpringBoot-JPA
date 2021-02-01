package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {


    @Autowired MemberRepository memberRepository;


    @Test
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("membarA");




        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }


}

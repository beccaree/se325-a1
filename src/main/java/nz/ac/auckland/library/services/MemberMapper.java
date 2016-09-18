package nz.ac.auckland.library.services;

import nz.ac.auckland.library.domain.Book;
import nz.ac.auckland.library.domain.Member;

/**
 * Helper class to convert between dto Member and domain Member
 * @author Rebecca Lee (rlee291)
 *
 */
public class MemberMapper {

	static Member toDomainModel(nz.ac.auckland.library.dto.Member dtoMember) {
		Member domainMember = new Member(
				dtoMember.getId(),
				dtoMember.getFirstname(),
				dtoMember.getLastname());
		return domainMember;
	}
	
	static nz.ac.auckland.library.dto.Member toDto(Member member) {
		nz.ac.auckland.library.dto.Member dtoMember = 
				new nz.ac.auckland.library.dto.Member(
						member.getId(),
						member.getFirstname(),
						member.getLastname());
		return dtoMember;
		
	}
	
}
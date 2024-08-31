package com.Security.FullStack.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="students")
public class StudentEntity implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1698541497973600964L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String studentName;
	
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	private int totalMarks;

	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
		}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority("STUDENT"));
		return list ;
	}

	@Override
	public String getUsername() {
		return email;
	}
}

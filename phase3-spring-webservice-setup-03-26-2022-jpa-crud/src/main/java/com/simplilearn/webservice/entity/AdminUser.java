package com.simplilearn.webservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

	@Entity
	@Table(name="admin_user")
	public class AdminUser {
		@Id
		@Column(name="email")
		private String email;
		
		@Column(name="pass")
		private String pass;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public AdminUser(String email, String pass) {
			super();
			this.email = email;
			this.pass = pass;
		}

		public AdminUser() {
			super();
		}

		@Override
		public String toString() {
			return "AdminUser [email=" + email + ", pass=" + pass + "]";
		}

		
}

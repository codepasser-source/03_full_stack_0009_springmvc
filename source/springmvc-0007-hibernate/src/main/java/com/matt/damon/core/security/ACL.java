package com.matt.damon.core.security;

import com.matt.damon.core.info.exception.MDErrorDescription;
import com.matt.damon.core.info.exception.MDException;

public class ACL {

	public static ThreadLocal<ACL> localACL = new ThreadLocal<ACL>();

	public static final int PROJECT_CREATE = 1;
	public static final int PROJECT_MEMBER_CREATE = 1 << 1;

	public static final int RECORD_QUERY = 1 << 2;
	public static final int RECORD_CREATE = 1 << 3;
	public static final int RECORD_DELETE = 1 << 4;
	public static final int RECORD_UPDATE = 1 << 5;

	public static final int ROLE_ADMIN_ACL = PROJECT_CREATE
			| PROJECT_MEMBER_CREATE;
	public static final int ROLE_PROJECT_MANAGER_ACL = PROJECT_MEMBER_CREATE;

	public static void main(String[] args) {
		int right = createACL(PROJECT_MEMBER_CREATE, PROJECT_MEMBER_CREATE);
		System.out.println(right);

		boolean result = hasACL(PROJECT_CREATE, right);
		System.out.println(result);

		result = hasACL(PROJECT_MEMBER_CREATE, right);
		System.out.println(result);

		result = hasACL(RECORD_QUERY, right);
		System.out.println(result);

		result = hasACL(RECORD_CREATE, right);
		System.out.println(result);

		result = hasACL(RECORD_DELETE, right);
		System.out.println(result);

		result = hasACL(RECORD_UPDATE, right);
		System.out.println(result);
	}

	public static ACL initACL() {
		ACL acl = new ACL();
		localACL.set(acl);
		return acl;
	}

	public static void checkACL(int acl, int right) throws Exception {
		if (!hasACL(acl, right)) {
			throw new MDException(MDErrorDescription.ERROR_CUSTOMER_AUTHORITY_0);
		}
	}

	public static boolean hasACL(int acl, int right) {
		return right != 0 && (right & acl) == acl;
	}

	public static int createACL(int... acl) {
		int right = 0;
		if (acl.length > 0) {
			for (int item : acl) {
				right |= item;
			}
		}
		return right;
	}
}
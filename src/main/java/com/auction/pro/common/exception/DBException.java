package com.auction.pro.common.exception;

public class DBException {
	// SQL execution error
	public static class BadExecution extends BaseException {
		private static final long serialVersionUID = 3555714415375055302L;

		public BadExecution(String msg) {
			super(msg);
		}
	}

	// No data exist
	public static class NoData extends BaseException {
		private static final long serialVersionUID = 8777415230393628334L;

		public NoData(String msg) {
			super(msg);
		}
	}

	// Invalid parameters error
	public static class InvalidParam extends BaseException {
		private static final long serialVersionUID = 4235225697094262603L;

		public InvalidParam(String msg) {
			super(msg);
		}
	}

}

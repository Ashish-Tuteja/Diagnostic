package com.auction.pro.common.exception;

public class CimbleExceptionCodes {
	public enum User {
		SAVE {
			@Override
			public int getCode() {
				return 0;
			}

			@Override
			public String getDeveloperMessage() {
				return "User not save";
			}
		},
		UPDATE {
			@Override
			public int getCode() {
				return 1;
			}

			@Override
			public String getDeveloperMessage() {
				return "User not update";
			}
		},
		GET {
			@Override
			public int getCode() {
				return 2;
			}

			@Override
			public String getDeveloperMessage() {
				return "Problem occur to getting users";
			}
		};

		public abstract int getCode();

		public abstract String getDeveloperMessage();

	}

	public enum Account {
		SAVE {
			@Override
			public int getCode() {
				return 0;
			}

			@Override
			public String getDeveloperMessage() {
				return "Account not save";
			}
		},
		UPDATE {
			@Override
			public int getCode() {
				return 1;
			}

			@Override
			public String getDeveloperMessage() {
				return "Account not update";
			}
		},
		GET {
			@Override
			public int getCode() {
				return 2;
			}

			@Override
			public String getDeveloperMessage() {
				return "Problem occur to getting accounts";
			}
		};

		public abstract int getCode();

		public abstract String getDeveloperMessage();

	}

	public enum Device {
		SAVE {
			@Override
			public int getCode() {
				return 0;
			}

			@Override
			public String getDeveloperMessage() {
				return "Device not save";
			}
		},
		UPDATE {
			@Override
			public int getCode() {
				return 1;
			}

			@Override
			public String getDeveloperMessage() {
				return "Device not update";
			}
		},
		GET {
			@Override
			public int getCode() {
				return 2;
			}

			@Override
			public String getDeveloperMessage() {
				return "Problem occur to getting devices";
			}
		};

		public abstract int getCode();

		public abstract String getDeveloperMessage();

	}

	public enum Vehicle {
		SAVE {
			@Override
			public int getCode() {
				return 0;
			}

			@Override
			public String getDeveloperMessage() {
				return "Vehicle not save";
			}
		},
		UPDATE {
			@Override
			public int getCode() {
				return 1;
			}

			@Override
			public String getDeveloperMessage() {
				return "Vehicle not update";
			}
		},
		GET {
			@Override
			public int getCode() {
				return 2;
			}

			@Override
			public String getDeveloperMessage() {
				return "Problem occur to getting vehicles";
			}
		};

		public abstract int getCode();

		public abstract String getDeveloperMessage();

	}

	public CimbleExceptionCodes() {
		// TODO Auto-generated constructor stub
	}
}

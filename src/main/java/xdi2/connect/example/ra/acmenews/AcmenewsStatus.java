package xdi2.connect.example.ra.acmenews;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

import xdi2.connect.core.ConnectResult;
import xdi2.core.syntax.CloudNumber;

public class AcmenewsStatus {

	private static Deque<Status> statuses = new ArrayDeque<Status> ();

	public static void newStatus(ConnectResult connectResult) {

		Status status = new Status();
		status.date = new Date();
		status.connectResult = connectResult;

		statuses.add(status);
		if (statuses.size() > 10) statuses.removeFirst();
	}

	public static String status() {

		StringBuffer buffer = new StringBuffer();

		for (Status status : statuses) {

			buffer.append(status.toString() + "\n");
		}

		return buffer.toString();
	}

	private static class Status {

		private Date date;
		private ConnectResult connectResult;

		private String getData() {

			CloudNumber cloudNumber = this.connectResult.getCloudNumber();
			if (cloudNumber == null) return null;

			return cloudNumber.toString();
		}

		@Override
		public String toString() {

			String data = this.getData();

			return this.date.toString() + ": " + data + "\n";
		}
	}
}

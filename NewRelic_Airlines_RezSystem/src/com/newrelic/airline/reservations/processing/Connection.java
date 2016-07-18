package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.CancelSearchRequest;
import com.newrelic.airline.reservations.forms.CreateFlightRequest;
import com.newrelic.airline.reservations.forms.DBRefreshRequest;
import com.newrelic.airline.reservations.forms.DeleteFlightRequest;
import com.newrelic.airline.reservations.forms.FindReservationRequest;
import com.newrelic.airline.reservations.forms.FlightRosterRequest;
import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.GetCitiesRequest;
import com.newrelic.airline.reservations.forms.GetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.ReservationCancelRequest;
import com.newrelic.airline.reservations.forms.ReservationRequest;
import com.newrelic.airline.reservations.forms.RevenueRequest;
import com.newrelic.airline.reservations.forms.RevenueSinceRequest;
import com.newrelic.airline.reservations.forms.ScheduleListRequest;
import com.newrelic.airline.reservations.forms.SearchIDRequest;
import com.newrelic.airline.reservations.forms.SetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.UpdateFlightRequest;
import com.newrelic.airline.reservations.pools.CancelSearchRequestPool;
import com.newrelic.airline.reservations.pools.CancellationRequestPool;
import com.newrelic.airline.reservations.pools.CreateFlightRequestPool;
import com.newrelic.airline.reservations.pools.DBRefreshRequestPool;
import com.newrelic.airline.reservations.pools.DeleteFlightRequestPool;
import com.newrelic.airline.reservations.pools.FlightRosterRequestPool;
import com.newrelic.airline.reservations.pools.FlightSearchRequestPool;
import com.newrelic.airline.reservations.pools.GetCitiesRequestPool;
import com.newrelic.airline.reservations.pools.GetScheduleRequestPool;
import com.newrelic.airline.reservations.pools.GetSearchIDRequestPool;
import com.newrelic.airline.reservations.pools.GetWaitPeriodRequestPool;
import com.newrelic.airline.reservations.pools.ReservationRequestPool;
import com.newrelic.airline.reservations.pools.RetrieveRequestPool;
import com.newrelic.airline.reservations.pools.RevenueRequestPool;
import com.newrelic.airline.reservations.pools.SetWaitPeriodRequestPool;
import com.newrelic.airline.reservations.pools.UpdateFlightRequestPool;

public class Connection {
	private ServerSocket serverSocket;
	private int port;
	private InetAddress address = null;
	
	private static final Logger LOG = Logger.getLogger(Connection.class);

	protected int getPort() {
		return port;
	}

	protected void setPort(int port) {
		this.port = port;
	}

	public Connection(byte[] IP, int p) {
		port = p;
		try {
			address = InetAddress.getByAddress(IP);
			setServerSocket();
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}

	}

	public Connection(InetAddress IP, int p) {
		port = p;
		try {
			address = IP;
			setServerSocket();
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}

	}

	public Connection(int p) {
		port = p;
		try {
			setServerSocket();
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}
	
	private void setServerSocket() throws IOException {
		if(serverSocket == null) {
			if(address != null) {
				serverSocket = new ServerSocket(port, 50, address);
			} else {
				serverSocket = new ServerSocket(port);
			}
		}
	}

	public void listen() throws IOException, ClassNotFoundException {

		LOG.info("Listening for requests on port "+port);

		while(true) {
			try {
				
				if (serverSocket != null) {
					Socket clientSocket = serverSocket.accept();
					LOG.debug("Request is Received");
					ObjectInputStream in = new ObjectInputStream(
							clientSocket.getInputStream());
					Object obj = in.readObject();
					if (ReservationRequest.class.isInstance(obj)) {
						LOG.debug("Request is ReservationRequest");
						ReservationRequest request = (ReservationRequest) obj;
						RezAgent agent = new RezAgent(request);
						agent.setClientSocket(clientSocket);
						ReservationRequestPool.getInstance().submit(agent);
						LOG.debug("Request submitted");
					} else if (FlightSearchRequest.class.isInstance(obj)) {

						LOG.debug("Request is FlightSearchRequest");
						FlightSearchRequest request = (FlightSearchRequest) obj;

						FlightSearchAgent t = new FlightSearchAgent(request);
						t.setClientSocket(clientSocket);
						FlightSearchRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (FindReservationRequest.class.isInstance(obj)) {
						LOG.debug("Request is FindReservationRequest");
						FindReservationRequest request = (FindReservationRequest) obj;

						RetrieveRezAgent t = new RetrieveRezAgent(request); // RetrieveRequestPool.getThreadFromPool();
						t.setRequest(request);
						t.setClientSocket(clientSocket);
						RetrieveRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (GetCitiesRequest.class.isInstance(obj)) {
						LOG.debug("Request is GetCitiesRequest");
						GetCitiesAgent t = new GetCitiesAgent();
						t.setClientSocket(clientSocket);
						GetCitiesRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (ReservationCancelRequest.class.isInstance(obj)) {
						LOG.debug("Request is ReservationCancelRequest");
						ReservationCancelRequest request = (ReservationCancelRequest) obj;

						CancellationAgent t = new CancellationAgent(); //CancellationRequestPool.getThreadFromPool();
						t.setClientSocket(clientSocket);
						t.setRequest(request);
						CancellationRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (ScheduleListRequest.class.isInstance(obj)) {
						LOG.debug("Request is ScheduleListRequest");
						ScheduleListAgent t = new ScheduleListAgent();
						t.setClientSocket(clientSocket);
						GetScheduleRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (SearchIDRequest.class.isInstance(obj)) {
						LOG.debug("Request is SearchIDRequest");
						SearchIDAgent t = new SearchIDAgent();
						SearchIDRequest request = (SearchIDRequest) obj;
						t.setRequest(request);
						t.setClientSocket(clientSocket);
						GetSearchIDRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (CancelSearchRequest.class.isInstance(obj)) {
						LOG.debug("Request is CancelSearchRequest");
						CancelSearchAgent t = new CancelSearchAgent();
						t.setClientSocket(clientSocket);
						CancelSearchRequest req = (CancelSearchRequest) obj;
						t.setRequest(req);
						CancelSearchRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if (DBRefreshRequest.class.isInstance(obj)) {
						LOG.debug("Request is DBRefreshRequest");
						DBRefreshAgent t = new DBRefreshAgent();
						t.setClientSocket(clientSocket);
						DBRefreshRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(GetWaitPeriodRequest.class.isInstance(obj)) {
						LOG.debug("Request is GetWaitPeriodRequest");
						GetWaitPeriodAgent t = new GetWaitPeriodAgent();
						t.setClientSocket(clientSocket);
						GetWaitPeriodRequest req = (GetWaitPeriodRequest)obj;
						t.setRequest(req);
						GetWaitPeriodRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(SetWaitPeriodRequest.class.isInstance(obj)) {
						LOG.debug("Request is SetWaitPeriodRequest");
						SetWaitPeriodAgent t = new SetWaitPeriodAgent();
						t.setClientSocket(clientSocket);
						SetWaitPeriodRequest req = (SetWaitPeriodRequest)obj;
						t.setRequest(req);
						SetWaitPeriodRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(CreateFlightRequest.class.isInstance(obj)) {
						LOG.debug("Request is CreateFlightRequest");
						CreateFlightAgent t = new CreateFlightAgent();
						t.setClientSocket(clientSocket);
						CreateFlightRequest req = (CreateFlightRequest)obj;
						t.setRequest(req);
						CreateFlightRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(DeleteFlightRequest.class.isInstance(obj)) {
						LOG.debug("Request is DeleteFlightRequest");
						DeleteFlightAgent t = new DeleteFlightAgent();
						t.setClientSocket(clientSocket);
						DeleteFlightRequest req = (DeleteFlightRequest)obj;
						t.setRequest(req);
						DeleteFlightRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(UpdateFlightRequest.class.isInstance(obj)) {
						LOG.debug("Request is UpdateFlightRequest");
						UpdateFlightAgent t = new UpdateFlightAgent();
						t.setClientSocket(clientSocket);
						UpdateFlightRequest req = (UpdateFlightRequest)obj;
						t.setRequest(req);
						UpdateFlightRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(FlightRosterRequest.class.isInstance(obj)) {
						LOG.debug("Request is FlightRoosterRequest");
						FlightRosterRequest req = (FlightRosterRequest)obj;
						GetFlightRosterAgent t = new GetFlightRosterAgent(req);
						t.setClientSocket(clientSocket);
						FlightRosterRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else if(RevenueRequest.class.isInstance(obj)) {
						LOG.debug("Request is RevenueRequest");
						RevenueRequest req = (RevenueRequest)obj;
						RevenueAgent t = new RevenueAgent(req);
						t.setClientSocket(clientSocket);
						RevenueRequestPool.getInstance().submit(t);
						LOG.debug("Request submitted");
					} else {
						LOG.debug("Request is not a known type: " + obj.getClass().getSimpleName());
						clientSocket.close();
					}
				} else {
					LOG.warn("Server Socket is null");
					int i = 0;
					while(serverSocket == null && i < 60) {
						synchronized (this) {
							try {
								wait(5000L);
							} catch (Exception e) {
							}
						}
						setServerSocket();
						i++;
					}
					if(serverSocket == null) {
						LOG.error("Unable to bind to server socket");
						System.exit(1);
					}
				}
			} catch (Exception e) {
				LOG.error("Error while listening on socket", e);
			}
		}
	}
}

package testtest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	
	private final static int PACKETSIZE = 10;
	static DatagramSocket socket = null;
	int Read = 1;
	int portin;
	int portout;
	String IP;
	
	public Server(int portin, int portout, String IP)
	{  
		this.portin = portin;
		this.portout = portout;
		this.IP = IP;			
	}
	public void ConnectSocket()
	{   
		try 
		{
			socket = new DatagramSocket(portin);
			System.out.println("Socket Connected");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		} 	       
	}
	public void sendData(byte[] send) 
	{	   
		try 
		{	
			InetAddress address = InetAddress.getByName(IP);
			DatagramPacket packet = new DatagramPacket(send, send.length, address, portout);
			socket.send(packet);
			packet.setData(new byte[PACKETSIZE]);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void StartListening() throws Exception
	{	
		Thread get = new Thread( new Runnable() 
		{
			@SuppressWarnings("unused")
			@Override
			public void run() 
			{
				try 
				{
					System.out.println("Server Started");
					while (Read == 1) 
					{
						System.out.println("Server Waiting");
						byte[] data = getData();
						System.out.println("Server Received data!");
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		get.start();			
	}
	
	private byte[] getData() 
	{
		byte[] receive = new byte[10];
		try 
		{
			DatagramPacket packet = new DatagramPacket(receive, PACKETSIZE);
			socket.receive(packet);
		}
		catch (Exception e) 
		{
			System.out.println(e);
	    }
		return receive;
	}
	public void close()
	{   
		Read = 0;
		try 
		{
			socket.close();
			System.out.println("Socket Connected");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		} 	       
	}
}

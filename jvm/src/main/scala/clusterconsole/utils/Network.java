package clusterconsole.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Network
{
	/**
	 * Finds a local, non-loopback, IPv4 address
	 * 
	 * @return The first non-loopback IPv4 address found, or
	 *         <code>null</code> if no such addresses found
	 * @throws SocketException
	 *            If there was a problem querying the network
	 *            interfaces
	 */
	public static String getLocalAddress() throws SocketException
	{
		Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
		while(ifaces.hasMoreElements())
		{
			NetworkInterface iface = ifaces.nextElement();
			Enumeration<InetAddress> addresses = iface.getInetAddresses();

			while(addresses.hasMoreElements())
			{
				InetAddress addr = addresses.nextElement();
				if(addr instanceof Inet4Address && !addr.isLoopbackAddress())
				{
					return addr.toString().substring(1);
				}
			}
		}

		return null;
	}
}









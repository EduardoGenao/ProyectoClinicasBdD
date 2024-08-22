package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread{

	public static void main (String args[])
	{
		ServerSocket sfd = null;
		try {
			sfd = new ServerSocket(7000);
		}
		catch(IOException ioe)
		{
			System.out.println("Comunicacion rechazada." + ioe);
			System.exit(1);
		}
		
		while(true)
		{
			try
			{
				Socket nsfd = sfd.accept();
				System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());
				
				DataInputStream fuente = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
				DataOutputStream archivoNuevo = new DataOutputStream(new FileOutputStream("clinica_respaldo.dat"));
				
				int byteLeido;
				while((byteLeido = fuente.read()) != -1)
					archivoNuevo.write(byteLeido);
				fuente.close();
				archivoNuevo.close();
				sfd.close();
			}
			catch(IOException ioe)
			{
				System.out.println("Error" +ioe);
			}
		}
	}
}
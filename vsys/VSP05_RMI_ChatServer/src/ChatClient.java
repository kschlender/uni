import java.rmi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends JFrame
{
	private static final long serialVersionUID = 1L;
	JTextArea output;
    JTextField input;
    ClientProxy clientProxy;
    IChatProxy chatProxy;
    String nickname;
    IChatServer server;

    public ChatClient(String nickname) throws Exception
    {
        server = (IChatServer) Naming.lookup("chat-server");
        clientProxy = new ClientProxy(this);
        chatProxy = server.subscribeUser(nickname, clientProxy);

        setTitle(nickname);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        output = new JTextArea();
        output.setEditable(false);
        output.setBackground(Color.black);
        output.setForeground(Color.white);
        JScrollPane scroller = new JScrollPane();
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getViewport().setView(output);
        getContentPane().add(scroller, BorderLayout.CENTER);
        input = new JTextField();
        input.setBackground(Color.black);
        input.setForeground(Color.white);
        getContentPane().add(input, BorderLayout.SOUTH);
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    sendMessage(input.getText());
                    input.setText("");
                }
                catch (RemoteException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        JButton close = new JButton("Abmelden");
        close.setBackground(Color.black);
        close.setForeground(Color.white);
        getContentPane().add(close, BorderLayout.NORTH);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        setSize(400, 300);
    }

    public void receiveMessage(String nickname, String message) {
        output.append(nickname + ": " + message + "\n");
        output.setCaretPosition(output.getText().length() - 1);
    }

    public void close()
    {
    	try
    	{
			server.unsubscribeUser(clientProxy);
		}
    	catch (RemoteException e) {}
    	System.exit(0);
    }

    public void sendMessage(String message) throws RemoteException
    {
        chatProxy.sendMessage(message);
    }
    public String getNickname()
    {
    	return nickname;
    }

    public static void main(String[] args) {
        try {
            String name = JOptionPane.showInputDialog(null, "Eingabe des Nickname");
            if (name != null && name.trim().length() > 0)
            {
                ChatClient client = new ChatClient(name);
                client.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Nickname mit mehr als 0 Zeichen ein.");
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
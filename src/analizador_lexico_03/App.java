package analizador_lexico_03;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class App extends JFrame {

	private JPanel contentPane;
	private static JTextPane txt_entrada;
	private static JTextPane txt_salida;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setTitle("Analizador Lexico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.NORTH);

		//creamos un objeto JMenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Verdana", Font.PLAIN, 12));
		//creamos los objetos JMenu
		JMenu abrir=new JMenu("Abrir");
		JMenu guardar=new JMenu("Guardar");
		JMenu formato=new JMenu("Formato");
		JMenu analizar=new JMenu("Analizar");
		JMenu cerrar=new JMenu("Cerrar");
		
		//creamos los objetos JMenuItem
				JMenuItem abrir_aut=new JMenuItem("Abrir Local");
				abrir.add(abrir_aut);
				JMenuItem abrir_file=new JMenuItem("Buscar Archivo");
				abrir.add(abrir_file);
				JMenuItem guardar_aut=new JMenuItem("Guardar Local");
				guardar.add(guardar_aut);
				JMenuItem guardar_file=new JMenuItem("Guardar Archivo");
				guardar.add(guardar_file);
				JMenuItem tamaño=new JMenuItem("Tamaño");
				formato.add(tamaño);
				JMenuItem analizar_entrada=new JMenuItem("Analizar Entrada");
				analizar.add(analizar_entrada);
				JMenuItem cerrar_file=new JMenuItem("Cerrar Programa");
				cerrar.add(cerrar_file);
				
				
				//asignamos un evento para los objetos JMenuItem
				GestionMenu_a1 abrir_local=new GestionMenu_a1();
				abrir_aut.addActionListener(abrir_local);
				GestionMenu_a abrir_ruta=new GestionMenu_a();
				abrir_file.addActionListener(abrir_ruta);
				GestionMenu_g1 guardar_local=new GestionMenu_g1();
				guardar_aut.addActionListener(guardar_local);
				GestionMenu_g guardar_arc=new GestionMenu_g();
				guardar_file.addActionListener(guardar_arc);
				GestionMenu_f formato_tam=new GestionMenu_f();
				tamaño.addActionListener(formato_tam);
				//
				GestionMenu_analizar analizar_entrada_evento=new GestionMenu_analizar();
				analizar_entrada.addActionListener(analizar_entrada_evento);
				//
				GestionMenu_c cerrar_ventana=new GestionMenu_c();
				cerrar_file.addActionListener(cerrar_ventana);
				panel.setLayout(new BorderLayout(0, 0));
				
				//agregamos los objetos JMenu al objeto JMenuBar
				menuBar.add(abrir);
				menuBar.add(guardar);
				menuBar.add(formato);
				menuBar.add(analizar);
				menuBar.add(cerrar);
				//agrgamos el objeto JMenuBar al panel para que asi pueda ser visible en la interfaz grafica	
				panel.add(menuBar, BorderLayout.NORTH);
				
				Panel panel_1 = new Panel();
				contentPane.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(null);
				
				JLabel lbl_salida = new JLabel("Salida:");
				lbl_salida.setFont(new Font("Verdana", Font.PLAIN, 12));
				lbl_salida.setBounds(10, 342, 67, 26);
				panel_1.add(lbl_salida);
				
				JLabel lbl_entrada = new JLabel("Entrada:");
				lbl_entrada.setFont(new Font("Verdana", Font.PLAIN, 12));
				lbl_entrada.setBounds(10, 11, 67, 26);
				panel_1.add(lbl_entrada);
				
		//		JScrollPane sp = new JScrollPane();
		//		sp.setBounds(10, 40, 855, 300);
		//		panel_1.add(sp);
				
				txt_entrada = new JTextPane();
		//		sp.setViewportView(txt_entrada);
				txt_entrada.setBounds(10, 40, 855, 300);	
				panel_1.add(txt_entrada);
				panel.setVisible (true);
				
				
				JScrollPane sp1 = new JScrollPane();
				sp1.setBounds(10, 369, 855, 150);
				
				//getContentPane().add(sp1,BorderLayout.CENTER);
				panel_1.add(sp1);
				
				txt_salida = new JTextPane();
				sp1.setViewportView(txt_salida);
				txt_salida.setBackground(Color.LIGHT_GRAY);
				panel.setVisible (true);
	}
	

	/*en este programa se crearon varias clases para definir los eventos de los objetos del programa*/
	//en esta clase se define como va a ser la funcion Abrir Local
private class GestionMenu_a1 implements ActionListener {
		
		FileReader fr;
		String linea;
	@Override
	public void actionPerformed(ActionEvent e) {
		String ruta="prueba.txt";
		String tem = "";
		File archivo = new File (ruta);
		
		try {
			
			fr = new FileReader (archivo);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Archivo no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		BufferedReader br = new BufferedReader(fr);
		try {
			
			
			linea = br.readLine();
	     //     System.out.println(linea);
	          
	          String n="";
	           while(linea != null)
	           {
	        //	   System.out.println(linea);   
	        	  tem+=n+linea;
	        	   linea = br.readLine();
	        	   n="\n";
	           }
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		linea=tem;
		txt_entrada.setText(linea);
		
	}
}
    //en esta clase se define la funcion Abrir Archivo
	private class GestionMenu_a implements ActionListener {
		
		FileReader fr;
		String linea;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String ruta="";
		
		ruta=JOptionPane.showInputDialog(null, "Intrudusca la ruta del fichero", "Abrir Fichero",JOptionPane.INFORMATION_MESSAGE);
		File archivo = new File (ruta);
		
		try {
			
			fr = new FileReader (archivo);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Archivo no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		BufferedReader br = new BufferedReader(fr);
		try {
			linea = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txt_entrada.setText(linea);
		
	}
}
	//en esta clase se define la funcion Guardar Local
	private class GestionMenu_g1 implements ActionListener {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
		public void actionPerformed(ActionEvent e) {
			String archivo=txt_entrada.getText();
		//	System.out.println(archivo);
		        try
		        {
		            fichero = new FileWriter("prueba.txt");
		            pw = new PrintWriter(fichero);
		            pw.println(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace();
			}
		        if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        JOptionPane.showMessageDialog(null, "Archivo Guardado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
				
	}
	}
	//en esta clase se define la funcion Guardar Archivo
	private class GestionMenu_g implements ActionListener {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
		public void actionPerformed(ActionEvent e) {
			String archivo=txt_entrada.getText();
		//	System.out.println(archivo);
			String ruta="";
			
			ruta=JOptionPane.showInputDialog(null, "Intrudusca la ruta del fichero", "Guardar Fichero",JOptionPane.INFORMATION_MESSAGE);
			
		        try
		        {
		            fichero = new FileWriter(ruta);
		            pw = new PrintWriter(fichero);
		            pw.println(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace();
			}
		        if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        JOptionPane.showMessageDialog(null, "Archivo Guardado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
				
	}
	}
	//en esta clase se define la funcion tamaño
	private class GestionMenu_f implements ActionListener {
		
        
		public void actionPerformed(ActionEvent e) {
			int tamaño;
			tamaño=Integer.parseInt(JOptionPane.showInputDialog(null,"Integre Tamaño de Letra","Tamaño de Letra",JOptionPane.INFORMATION_MESSAGE));
			txt_entrada.setFont(new Font("Verdana",Font.PLAIN,tamaño));
				
	}
	}
	//en esta clase se define la funcion cerrar
	private class GestionMenu_c implements ActionListener {
		

	@Override
	public void actionPerformed(ActionEvent e) {
        
        if(JOptionPane.showConfirmDialog (null, "Deseas Cerrar El Programa?","Cerrar",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }else {
              
            }
          }
		
		
	}
	//en esta clase se define la funcion revisar tokens
		private static class GestionMenu_analizar implements ActionListener {
			
		
			public void actionPerformed(ActionEvent e) {
				
				File archivo = new File ("archivo.txt");
				PrintWriter escribir;
				try 
				{
				escribir=new PrintWriter(archivo);
				escribir.print(txt_entrada.getText());
				escribir.close();
				
				}
				catch(FileNotFoundException ex) 
				{
					ex.printStackTrace();
				}
				
				try {
					Reader lector = new BufferedReader(new FileReader("archivo.txt"));
					A_Lexico lexer = new A_Lexico(lector);
					String resultado="";
					while (true) {
						Tokens tokens =lexer.yylex();
						if(tokens == null) 
						{
							resultado+= "\n...";
							txt_salida.setText(resultado);
							
							return;
							
							
						
						}
						
						switch(tokens) 
						{
						case ERROR:
						//	resultado+="Simbolo no definido\n";
							resultado="\n"+lexer.lexeme;
							break;
						case Identificador:
						case Numero:
						case Reservadas:
						case Sentencia_SELECT:
						case Sentencia_INSERT:
						case Sentencia_UPDATE:
						case Sentencia_DELETE:
						case Terminacion:
						case ID:
						
							resultado+="\n"+ lexer.lexeme+" Es un "+tokens+"\n";
							break;
							default:
								resultado+="\n"+"Token: "+tokens+"\n";
								break;
						}
						
					}
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
				
				
		}
}

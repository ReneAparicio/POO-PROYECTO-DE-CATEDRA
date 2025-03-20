import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;

public class MultiWorksGroup extends JFrame {
    DefaultTableModel mCli, mEmp, mAct, mCot;

    public MultiWorksGroup() {
        super("Multi-Works Group");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x90BCF5));
        setLayout(new BorderLayout());
        add(new JLabel("¡Bienvenido a Multi-Works Group!", SwingConstants.CENTER), BorderLayout.NORTH);
        JTabbedPane tabs = new JTabbedPane();

        mCli = new DefaultTableModel(new Object[]{"ID","Nombre","Documento","Tipo Persona","Teléfono","Correo","Dirección","Estado","Creado Por","Fecha Creación","Fecha Actualización","Fecha Inactivación"},0);
        tabs.add("Cliente", createPanel(mCli, "Agregar Cliente", e -> addCli()));

        mEmp = new DefaultTableModel(new Object[]{"ID","Nombre","Documento","Tipo Persona","Tipo Contratación","Teléfono","Correo","Dirección","Estado","Creado Por","Fecha Creación","Fecha Actualización","Fecha Inactivación"},0);
        tabs.add("Empleado", createPanel(mEmp, "Agregar Empleado", e -> addEmp()));

        mAct = new DefaultTableModel(new Object[]{"ID Asignación","Título Subtarea","Descripción Subtarea"},0);
        tabs.add("Actividad", createPanel(mAct, "Agregar Actividad", e -> addAct()));

        mCot = new DefaultTableModel(new Object[]{"Título Actividad","Trabajador","Área","Costo/hora","Inicio","Fin","Horas approx","Incremento (%)","Total"},0);
        tabs.add("Cotización", createPanel(mCot, "Agregar Cotización", e -> addCot()));

        add(tabs, BorderLayout.CENTER);
    }
    JPanel createPanel(DefaultTableModel m, String txt, java.awt.event.ActionListener al) {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(new JTable(m)), BorderLayout.CENTER);
        JButton b = new JButton(txt);
        b.addActionListener(al);
        p.add(b, BorderLayout.SOUTH);
        return p;
    }

    void addCli() {
        try {
            int id = Integer.parseInt(get("ID único del Cliente:"));
            mCli.addRow(new Object[]{id, get("Nombre"), get("Documento"), get("Tipo de Persona"), get("Teléfono"), get("Correo"), get("Dirección"), get("Estado"), get("Creado Por"), get("Fecha de Creación"), get("Fecha de Actualización"), get("Fecha de Inactivación")});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en Cliente");
        }
    }

void addCot() {
        try {
            String titulo = get("Título Actividad:"), trabajador = get("Trabajador asignado:"), area = get("Área asignada:");
            double costoHora = Double.parseDouble(get("Costo/hora:"));
            String inicio = get("Fecha y hora de inicio:"), fin = get("Fecha y hora de fin:");
            double horas = Double.parseDouble(get("Cantidad de horas aproximadas:"));
            double incremento = Double.parseDouble(get("Incremento extra (%):"));
            double total = (costoHora * horas) + ((costoHora * horas) * incremento / 100.0);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            mCot.addRow(new Object[]{titulo, trabajador, area, costoHora, inicio, fin, horas, incremento, nf.format(total)});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en Cotización");
        }
    }

    String get(String p){ return JOptionPane.showInputDialog(this, p); }
} 

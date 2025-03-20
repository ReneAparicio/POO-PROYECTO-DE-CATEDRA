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

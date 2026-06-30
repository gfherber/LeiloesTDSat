import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

public class vendasVIEW extends JFrame {

    private JTable listaProdutosVendidos;
    private JScrollPane jScrollPane1;
    private JLabel jLabel1;
    private JButton btnVoltar;

    public vendasVIEW() {
        initComponents();
        listarProdutosVendidos();
    }

    private void initComponents() {
        jLabel1 = new JLabel("Lista de Produtos Vendidos");
        jLabel1.setFont(new java.awt.Font("Lucida Fax", 0, 18));
        
        listaProdutosVendidos = new JTable();
        listaProdutosVendidos.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "Valor", "Status"}
        ));
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(listaProdutosVendidos);

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(540, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        jLabel1.setBounds(160, 20, 300, 30);
        jScrollPane1.setBounds(40, 60, 450, 200);
        btnVoltar.setBounds(400, 290, 90, 30);

        add(jLabel1);
        add(jScrollPane1);
        add(btnVoltar);
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) listaProdutosVendidos.getModel();
            model.setNumRows(0);
            
            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();
            
            for(int i = 0; i < listagem.size(); i++){
                model.addRow(new Object[]{
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela de vendas: " + e.getMessage());
        }
    }
}
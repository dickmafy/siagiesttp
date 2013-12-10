package modules.evaluaciones.domain;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;

import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;  

@ManagedBean(name = "treeUniverso")
@SessionScoped

public class TreeUniverso implements Serializable
{
				
	private TreeNode root;
    private TreeNode selectedNode;
    private String buscar;  

    public TreeUniverso() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Lima", root);
        TreeNode node1 = new DefaultTreeNode("Cajamarca", root);
        TreeNode node2 = new DefaultTreeNode("Piura", root);
        TreeNode node3 = new DefaultTreeNode("La Libertad", root);  
        TreeNode node4 = new DefaultTreeNode("Ica", root);
        TreeNode node5 = new DefaultTreeNode("Ayacucho", root);
        TreeNode node6 = new DefaultTreeNode("Puno", root);

        TreeNode node00 = new DefaultTreeNode("Lima", node0);
        TreeNode node01 = new DefaultTreeNode("Barranca", node0); 
        TreeNode node02 = new DefaultTreeNode("Cajatambo", node0);
        TreeNode node03 = new DefaultTreeNode("Cañete", node0); 
        TreeNode node04 = new DefaultTreeNode("Canta", node0);          
                
        TreeNode node10 = new DefaultTreeNode("Cajamarca", node1);
        TreeNode node11 = new DefaultTreeNode("San Ignacio", node1);
        TreeNode node12 = new DefaultTreeNode("Jaen", node1);
        TreeNode node13 = new DefaultTreeNode("Chota", node1);  
        TreeNode node14 = new DefaultTreeNode("Celendin", node1);
         
        //Lima - Lima
        TreeNode node000 = new DefaultTreeNode("Manuel Arevalo Cáceres", node00);
        TreeNode node001 = new DefaultTreeNode("Tello", node00);
        TreeNode node002 = new DefaultTreeNode("Argentina", node00);  
        TreeNode node003 = new DefaultTreeNode("Mariscal Caceres", node00);
        TreeNode node004 = new DefaultTreeNode("Miguel Grau", node00);
            
        //Lima - Barranca
        TreeNode node010 = new DefaultTreeNode("Victor Raúl", node01);  
        TreeNode node011 = new DefaultTreeNode("Carlos Moreno", node01);
        TreeNode node012 = new DefaultTreeNode("Manuel Gonzales Prada", node01);
        TreeNode node013 = new DefaultTreeNode("Cayetano Heredia", node01);  

        
    }  

    public void buscarSRN(){
        busquedaRecur(getRoot());
        if (getSelectedNode()==null){
            System.out.println("No ha encontrado nada");
            root.setSelected(true);
            setSelectedNode(root);
        }else{
            getSelectedNode().setSelected(true);
            contraeTree(getRoot());
            expandePadre(getSelectedNode());
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void contraeTree(TreeNode node) {
        TreeNode child;
        for (int i = 0; i < node.getChildCount(); i++) {
            child = node.getChildren().get(i);
            child.setExpanded(false);
            if (child.getChildCount() >= 1) {
                busquedaRecur(child);
            }
        }
    }

    public void expandePadre (TreeNode child){
        if (child.getParent()!=null){
            child.getParent().setExpanded(true);
            expandePadre(child.getParent());
        }
    }

    public TreeNode busquedaRecur(TreeNode root) {
        TreeNode child;
        for (int i = 0; i < root.getChildCount(); i++) {
            child = root.getChildren().get(i);
            if (child.toString().equals(getBuscar())) {
                setSelectedNode(child);
                return getSelectedNode();
            } else if (child.getChildCount() >= 1) {
                busquedaRecur(child);
            }
        }
        return null;
    }
    public void onNodeSelect(NodeSelectEvent event){

        System.out.println ("XCR **** Nodo seleccionado listener");
    }

    public TreeNode getRoot() {
        return root;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
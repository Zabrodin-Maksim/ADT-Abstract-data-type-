package AbstrTable;

import DoubleList.DoubleListException;
import Fronta.AbstrFifo;
import Zasobnik.AbstrLifo;
import Zasobnik.IAbstrLifo;
import enums.eTypProhl;

import java.util.Iterator;


public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V> {
    private Node<K, V>  rootNode;

    private class Node<K, V>{
        private V value;
        private K key;
        private Node leftChild, rightChild, parentNode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }
    }

    @Override
    public void zrus() {
        rootNode = null;
    }

    @Override
    public boolean jePrazdny() {
        if(rootNode == null) {
            return true;
        } else
            return false;
    }

    @Override
    public V najdi(K key) {
        if (jePrazdny()) {return null;}
        Node<K, V> currentNode = rootNode;
        while (currentNode.getKey() != key) {
            if (key.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode.getValue();
    }

    @Override
    public void vloz(K key, V value) {
        Node newNode = new Node( value , key);
        if (rootNode == null){
            rootNode = newNode;
        }                                       //есть список?
        else {
            Node<K, V> currentNode = rootNode;
            Node<K, V> predNode;
            while (true){
                predNode = currentNode;
                if(key == currentNode.getKey()){
                    return;
                }
                else if (key.compareTo( currentNode.getKey()) < 0) {        //Left
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){
                        predNode.setLeftChild(newNode);
                        predNode.getLeftChild().setParentNode(predNode);
                        return;
                    }
                }

                else {
                    currentNode = currentNode.getRightChild();              //Right
                    if(currentNode == null){
                        predNode.setRightChild(newNode);
                        predNode.getRightChild().setParentNode(predNode);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public V odeber(K key) {
        Node<K, V> currentNode = rootNode;
        Node<K, V> predNode = rootNode;
        Node<K, V> del = null;
        boolean itLeftChild = true;
        while (currentNode.getKey() != key){                                                    // поиск удаляемого элемента
            predNode = currentNode;
            if (key.compareTo(currentNode.getKey()) < 0){
                itLeftChild = true;
                currentNode = currentNode.getLeftChild();
                del = currentNode;
            }
            else {
                itLeftChild = false;
                currentNode = currentNode.getRightChild();
                del = currentNode;
            }
            if (currentNode == null)
                return null;
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null){         // если нет детей
            if (currentNode == rootNode){
                rootNode = null;
            }
            else if (itLeftChild) {
                predNode.setLeftChild(null);
            }
            else
            predNode.setRightChild(null);
        }

        else if (currentNode.getRightChild() == null) {         //нет правого ребенка
            if(currentNode == rootNode) {
                rootNode = currentNode.getLeftChild();
            }else if (itLeftChild) {
                predNode.setLeftChild(currentNode.getLeftChild());
            } else {
                predNode.setRightChild(currentNode.getLeftChild());
            }

        } else if (currentNode.getLeftChild() == null) {        //нет левого ребенка
            if (currentNode == rootNode){
                rootNode = currentNode.getRightChild();
            }else if (itLeftChild){
                predNode.setLeftChild(currentNode.getRightChild());
            }else {
                predNode.setRightChild(currentNode.getRightChild());
            }
        }
        else {                                                  //есть два потомка, узел заменить преемником
            Node<K, V> priem = najdPriem(currentNode);
            if (currentNode == rootNode){
                rootNode = priem;
            } else if (itLeftChild) {
                predNode.setLeftChild(priem);
            }else {
                predNode.setRightChild(priem);
            }
        }
        return del.value;
    }

    @Override
    public Iterator<V> vytvorIterator(eTypProhl typ) {
        switch (typ){
            case HLOUBKA -> {
                return hloubka();
            }
            case SIRKA -> {
                return sirka();
            }
        }
        return null;
    }
    private Iterator<V> hloubka(){
        AbstrLifo<Node<K, V>> zasobnik = new AbstrLifo<>();       //zasobnik
        zasobnik.vloz(rootNode);

        return new Iterator<V>() {
            Node<K, V> currentNode = rootNode;
            @Override
            public boolean hasNext() {
                return !zasobnik.jePrazdny();
            }

            @Override
            public V next() {
                if(!hasNext()){
                    return null;
                }
                while (currentNode != null && currentNode.leftChild != null){
                    zasobnik.vloz(currentNode.leftChild);
                    currentNode = currentNode.leftChild;
                }

                Node<K, V> element = zasobnik.odeber();

                if (element.rightChild != null){
                    zasobnik.vloz(element.rightChild);
                    currentNode = element.rightChild;
                }

                return element.value;
            }
        };
    }
    private Iterator<V> sirka(){
        AbstrFifo<V> fronta = new AbstrFifo<>();
        AbstrFifo<Node<K, V>> lifo = new AbstrFifo<>();
        lifo.vloz(rootNode);
        while (!lifo.jePrazdny()){
            Node<K, V> actual= lifo.odeber();
            fronta.vloz(actual.value);
            if (actual.leftChild != null){
                lifo.vloz(actual.leftChild);
            }
            if (actual.rightChild != null){
                lifo.vloz(actual.rightChild);
            }
        }
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !fronta.jePrazdny();
            }

            @Override
            public V next() {
                return fronta.odeber();
            }
        };
    }
    private Node najdPriem(Node node){
        Node<K, V> predNode = node;
        Node<K, V> priem = node;
        Node<K, V> currentNode = node.getRightChild();
        while (currentNode !=null){
            predNode = priem;
            priem = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        if (priem != node.getRightChild()){
            predNode.setLeftChild(priem.getRightChild());
            priem.setRightChild(node.getRightChild());
        }
        return priem;
    }                   // поиск приемника

    public V najdiNejblizsi(K key){
        Node<K, V> actual = rootNode;
        int nejMensi = Integer.MAX_VALUE;
        V nejMen = null;
        while(actual != null){
            int comp = key.compareTo(actual.key);
            int cislo = (int) Math.sqrt(Math.abs(comp*comp - nejMensi*nejMensi));
            if(cislo < nejMensi){
                nejMensi = cislo;
                nejMen = actual.value;
            }
            if(comp > 0) actual = actual.rightChild;
            else if (comp < 0) actual = actual.leftChild;
            else {
                return actual.value;
            }
        }
        return nejMen;
    }


}

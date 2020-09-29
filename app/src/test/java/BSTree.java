public class BSTree {
    StringProperty sp;
    BSTree left, right;
    int num;

    BSTree(StringProperty sp) {
        this.sp = sp;
        this.num = 1;
        this.left = new BSTree();
        this.right = new BSTree();
    }

    BSTree() {
        this.sp = new StringProperty();
        this.num = 0;
    }

    boolean add(StringProperty element) {
        if (element.compareTo(sp) < 0) {
            if (left == null) {
                left = new BSTree(element);
                num++;
                return true;
            }
            return left.add(element); //recursively find the location of the element until there is a null.
        } else if (element.compareTo(sp) > 0) {
            if (right == null) {
                right = new BSTree(element);
                num++;
                return true;
            }
            return right.add(element);
        } else {
            return false;
        }
    }

//    public void traverse(){
//        if(this.sp.id!=null){
//            this.left.traverse();
////			System.out.println(x.value);
//            if((this.left!=null&&this.right==null)||(this.left==null&&this.right!=null)){
//                System.out.println(this.sp.id);
//            }
//            this.right.traverse();
//        }
//
//    }


    @Override
    public String toString() {
        return "BSTree{" +
                ", sp=" + sp.id +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

package io.github.javaasasecondlanguage.lecture02.practice.tree.quant;

import io.github.javaasasecondlanguage.lecture02.practice.tree.AbstractTree;
import io.github.javaasasecondlanguage.lecture02.practice.tree.TreeNode;

public class QuantTreeImpl extends AbstractTree<Integer> implements QuantTree {
    public QuantTreeImpl(TreeNode<Integer> root) {
        super(root);
    }

    @Override
    public int getMaxHeight() {
        var root = this.getRoot();
        QuantTreeImpl.maxHeight = 0;
        recInnerCheck(root, 1);
        return QuantTreeImpl.maxHeight;
    }

    public static int maxHeight = 0;

    public void recInnerCheck(TreeNode<Integer> root, int height) {
        if(root == null) {
            QuantTreeImpl.maxHeight = Math.max(height-1, QuantTreeImpl.maxHeight);
            return;
        }
        if(root == null || root.getChildren() == null || root.getChildren().size() == 0) {
            QuantTreeImpl.maxHeight = Math.max(height, QuantTreeImpl.maxHeight);
            return;
        }
        for (var node : root.getChildren()) {
            recInnerCheck(node, height + 1);
        }
    }
}

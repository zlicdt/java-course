package com.zlicdt.gui;

import javax.swing.*;
import java.awt.*;

/**
 * 抽象的基础面板类，作为所有应用面板的父类
 * 提供共同的功能和结构
 */
public abstract class BasePanel extends JPanel {
    
    protected Frame parentFrame;
    
    /**
     * 基础面板的构造函数
     * @param parentFrame 父框架引用
     */
    public BasePanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        initializePanel();
    }
    
    /**
     * 初始化面板的抽象方法，子类必须实现
     */
    protected abstract void initializePanel();
    
    /**
     * 更新面板显示的方法
     * 子类可以根据需要覆盖此方法
     */
    public void updateDisplay() {
        // 默认实现为空，由子类根据需要覆盖
    }
    
    /**
     * 创建并返回标题标签
     * @param title 标题文本
     * @return 格式化的标题标签
     */
    protected JLabel createTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("", Font.BOLD, 24));
        return titleLabel;
    }
    
    /**
     * 创建并返回按钮面板
     * @param flowAlignment 按钮对齐方式，使用FlowLayout常量
     * @return 按钮面板
     */
    protected JPanel createButtonPanel(int flowAlignment) {
        JPanel buttonPanel = new JPanel(new FlowLayout(flowAlignment));
        return buttonPanel;
    }
}
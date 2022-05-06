#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QToolBar>
#include <QIcon>
#include <QAction>
#include <QHBoxLayout>
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setWindowTitle("Leonidas Project");
    this->setMainToolBar();

}

void MainWindow:: setMainToolBar(){

    //pixmap to enhance the toolbar
    QPixmap openpix(":/ressources/icon/open.png");
    QPixmap closepix(":/ressources/icon/close.png");
    QPixmap aboutpix(":/ressources/icon/info.png");
    //  QPixmap quitpix(":/ressources/icon/quit.png");

    toolBar = new QToolBar("Tool Bar");
    toolBar->setFloatable(false);
    toolBar->setMovable(false);
    this->addToolBar(toolBar);

    //tool to center the icons on the toolbar
    auto dummyBoy1 = new QWidget(this);
    dummyBoy1->setSizePolicy(QSizePolicy::Expanding, QSizePolicy::Expanding);
    auto dummyBoy2 = new QWidget(this);
    dummyBoy2->setSizePolicy(QSizePolicy::Expanding, QSizePolicy::Expanding);



    //Action management
    toolBar->addWidget(dummyBoy1);


    QAction *openAction= toolBar->addAction(QIcon(openpix),"Open File");
    toolBar->addSeparator();
    QAction *closeAction= toolBar->addAction(QIcon(closepix),"Close File");
    toolBar->addSeparator();
    QAction *aboutAction= toolBar->addAction(QIcon(aboutpix),"About");
    toolBar->addWidget(dummyBoy2);
    //  QAction *quitAction= toolBar->addAction(QIcon(quitpix),"Leave App");

    //signals management
    connect(openAction, &QAction::triggered, this, &MainWindow :: openFile);
    connect(closeAction, &QAction::triggered, this,&MainWindow :: closeFile);
    connect(aboutAction, &QAction::triggered, this,&MainWindow :: about);
    //   connect(quitAction, &QAction::triggered, this, &QApplication::quit);
}

//signals implem

void MainWindow::openFile(){
    //TODO
}
void MainWindow::closeFile(){
    //TODO
}
//About message box
void MainWindow::about(){
        QMessageBox msgBox;
        msgBox.setWindowTitle("About Leonidas");
        msgBox.setText("Project made for RI50 Course at UTBM. \nMade by \n Jules LAMY\n Théo BEDEZ\n Feiyang YIN\n Yu Xiang LI\n Adrien PAYSANT.");
        msgBox.exec();
}


MainWindow::~MainWindow()
{
    delete ui;
}

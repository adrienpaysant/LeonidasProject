#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QToolBar>
#include <QIcon>
#include <QAction>

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
    QPixmap openpix("");
    QPixmap closepix("");
    QPixmap aboutpix("");
    QPixmap quitpix("");

    toolBar = addToolBar("Tool Bar");
    toolBar->addSeparator();
    toolBar->setFloatable(false);


    //Action management
    QAction *openAction= toolBar->addAction(QIcon(openpix),"Open File");
    QAction *closeAction= toolBar->addAction(QIcon(closepix),"Close File");
    QAction *aboutAction= toolBar->addAction(QIcon(aboutpix),"About");
    QAction *quitAction= toolBar->addAction(QIcon(quitpix),"Leave App");

    //signals management
    connect(openAction, &QAction::triggered, this, &MainWindow :: openFile);
    connect(closeAction, &QAction::triggered, this,&MainWindow :: closeFile);
    connect(aboutAction, &QAction::triggered, this,&MainWindow :: about);
    connect(quitAction, &QAction::triggered, this, &QApplication::quit);
}

//signal implem
void MainWindow::about(){
    //TODO
}
void MainWindow::openFile(){
    //TODO
}
void MainWindow::closeFile(){
    //TODO
}


MainWindow::~MainWindow()
{
    delete ui;
}


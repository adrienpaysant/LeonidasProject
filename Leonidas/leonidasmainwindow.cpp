#include "leonidasmainwindow.h"
#include "./ui_leonidasmainwindow.h"

LeonidasMainWindow::LeonidasMainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::LeonidasMainWindow)
{
    ui->setupUi(this);
}

LeonidasMainWindow::~LeonidasMainWindow()
{
    delete ui;
}


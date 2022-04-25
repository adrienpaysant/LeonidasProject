#include "mainwindow.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    a.setWindowIcon(QIcon(":/ressources/logo_leonidas/leonidas_logo.png"));
    w.show();
    return a.exec();
}

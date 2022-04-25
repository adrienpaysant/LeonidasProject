#include "leonidasmainwindow.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    LeonidasMainWindow w;
    w.show();
    return a.exec();
}

#ifndef LEONIDASMAINWINDOW_H
#define LEONIDASMAINWINDOW_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class LeonidasMainWindow; }
QT_END_NAMESPACE

class LeonidasMainWindow : public QMainWindow
{
    Q_OBJECT

public:
    LeonidasMainWindow(QWidget *parent = nullptr);
    ~LeonidasMainWindow();

private:
    Ui::LeonidasMainWindow *ui;
};
#endif // LEONIDASMAINWINDOW_H

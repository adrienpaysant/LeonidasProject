#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "leonidasabout.h"
#include <QMainWindow>


QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();


private:
    LeonidasAbout *lAbout;
    Ui::MainWindow *ui;
    QToolBar *toolBar;
    void setMainToolBar();
    private slots:
      void openFile();
      void closeFile();
      void about();

};
#endif // MAINWINDOW_H

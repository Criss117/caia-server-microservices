import DashBoardNavBar from "@/core/dashboard/presentation/components/dasboard-navbar";
import DashBoardSideBar from "@/core/dashboard/presentation/components/dashboard-sidebar";
import { PropsWithChildren } from "react";

const DashboardLayout = ({ children }: PropsWithChildren) => {
  return (
    <main className="h-screen">
      <DashBoardNavBar />
      <DashBoardSideBar>{children}</DashBoardSideBar>
    </main>
  );
};

export default DashboardLayout;

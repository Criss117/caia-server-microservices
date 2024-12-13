import { Avatar, AvatarFallback } from "@/core/shared/components/ui/avatar";
import { Button } from "@/core/shared/components/ui/button";
import CaiaTitle from "@/core/shared/components/ui/caia-title";
import { ROUTES } from "@/core/shared/lib/constants/routes";
import { cn } from "@/core/shared/lib/utils";
import { PlusCircle } from "lucide-react";
import Link from "next/link";

interface Props {
  className?: string;
}

const DashBoardNavBar = ({ className }: Props) => {
  return (
    <header
      className={cn(
        "mx-auto py-2 border-b bg-lightbg-100 fixed top-0 w-full z-50",
        className
      )}
    >
      <nav className="flex items-center justify-between w-[80%] mx-auto">
        <div className="flex gap-x-10 w-full">
          <CaiaTitle toDashboard />
        </div>
        <div className="w-full flex justify-end gap-x-10 items-center">
          <Button className="bg-lightprimary-200 space-x-2" asChild>
            <Link href={ROUTES.DASHBOARD.CREATE_CONFERENCES}>
              <PlusCircle className="w-5 h-5" />
              <p>Crear Conferencia</p>
            </Link>
          </Button>
          <Avatar>
            <AvatarFallback className="bg-lightprimary-200 text-white">
              CV
            </AvatarFallback>
          </Avatar>
        </div>
      </nav>
    </header>
  );
};

export default DashBoardNavBar;

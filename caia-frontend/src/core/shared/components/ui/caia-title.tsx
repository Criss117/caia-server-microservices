import Link from "next/link";
import { cn } from "../../lib/utils";
import { ROUTES } from "../../lib/constants/routes";

interface Props {
  className?: string;
  toDashboard?: boolean;
}

const CaiaTitle = ({ className, toDashboard = false }: Props) => {
  return (
    <Link
      className={cn("font-bold text-3xl text-lightprimary-200", className)}
      href={toDashboard ? ROUTES.DASHBOARD.ROOT : ROUTES.HOME}
    >
      C.A.I.A
    </Link>
  );
};

export default CaiaTitle;

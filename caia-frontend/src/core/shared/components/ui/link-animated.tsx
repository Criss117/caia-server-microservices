import Link from "next/link";
import { PropsWithChildren } from "react";
import { cn } from "../../lib/utils";

interface Props extends PropsWithChildren {
  href: string;
  className?: string;
  onClick?: () => void;
}

const LinkAnimated = ({ children, className, href, onClick }: Props) => {
  return (
    <Link
      href={href}
      className={cn(
        `relative
        after:content-['']
        after:bg-black 
        after:border-b-2 
        after:w-0 
        after:h-1 
        after:absolute 
        after:bottom-0 
        after:left-0 
        after:hover:w-full 
        after:transition-all`,
        className
      )}
      onClick={onClick}
    >
      {children}
    </Link>
  );
};

export default LinkAnimated;
